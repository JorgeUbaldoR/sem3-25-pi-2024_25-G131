.section .text

.global move_n_to_array

move_n_to_array:

    # Salva os registradores r8 e rsi na pilha para preservá-los
	pushq %r8
	pushq %rsi

    # Chama a função auxiliar para calcular o número de elementos no buffer
    call get_n_element

    # Restaura os valores originais de rsi e r8 da pilha
    popq %rsi
    popq %r8

    # Verifica se o retorno da função é <= 0 (erro ou buffer vazio)
    cmpl $0, %eax
    jle error

    # Verifica se o número de elementos disponíveis é menor que `n`
    cmpl %eax, %r8d
    jg error

    # Verifica se `n` é negativo
    cmpl $0, %r8d
    jl error

    # Carrega o valor atual de `head` em ebx
    movl (%rcx), %ebx  # HEAD
    movl $0, %r10d     # Inicializa o contador de elementos removidos (r10d)

    # Ajusta o índice máximo do buffer (length - 1)
    subl $1, %esi      # `length` - 1

    # Compara `head` com `tail` para decidir a ordem de leitura
    cmpl (%rdx), %ebx
    jg tail_first      # Se `head > tail`, o buffer está dividido (caso circular)

loop_head_first:
    # Verifica se o número de elementos já foi movido
    cmpl %r10d, %r8d
    je loop_end        # Sai do loop se todos os elementos foram processados

    # Move o elemento do buffer para o array
    movl (%rdi, %rbx, 4), %r11d  # Carrega o elemento do buffer
    movl %r11d, (%r9, %r10, 4)  # Salva no array de destino

    # Incrementa `head` e o contador
    incl %ebx   # Próximo elemento do buffer
    incl %r10d  # Incrementa o contador de elementos movidos

    # Continua o loop
    jmp loop_head_first

tail_first:
    # Verifica se o número de elementos já foi movido
    cmpl %r8d, %r10d
    je loop_end        # Sai do loop se todos os elementos foram processados

    # Move o elemento do buffer para o array
    movl (%rdi, %rbx, 4), %r11d  # Carrega o elemento do buffer
    movl %r11d, (%r9, %r10, 4)  # Salva no array de destino

    # Verifica se `head` atingiu o final do buffer
    cmpl %ebx, %esi
    je head_restart    # Reinicia `head` para o início do buffer

    # Incrementa `head` e o contador
    incl %ebx   # Próximo elemento do buffer
    incl %r10d  # Incrementa o contador de elementos movidos

    # Continua o loop
    jmp tail_first

head_restart:
    # Reseta `head` para o início do buffer (índice 0)
    movl $0, %ebx    # Move `head` para o início
    incl %r10d       # Incrementa o contador de elementos movidos

    # Continua o loop
    jmp tail_first

loop_end:
    # Verifica se todos os elementos disponíveis foram movidos
    cmpl %r8d, %eax
    je end_remove_all  # Caso especial: remover todos os elementos disponíveis

    # Atualiza o valor de `head` no buffer
    movl %ebx, (%rcx) # Atualiza `head`
    movl $1, %eax     # Indica sucesso

    ret               # Retorna

end_remove_all:
    # Ajusta `head` para o último elemento processado
    decl %ebx         # Decrementa `head`
    movl %ebx, (%rcx) # Atualiza `head` no buffer
    movl $1, %eax     # Indica sucesso

    ret               # Retorna

error:
    # Indica falha
    movl $0, %eax     # Retorna 0 para indicar erro

    ret               # Retorna
