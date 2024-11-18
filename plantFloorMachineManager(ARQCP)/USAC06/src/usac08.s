.section .text

.global move_n_to_array

move_n_to_array:
	
	pushq %r8
	pushq %rsi
	
    call get_n_element
    
    popq %rsi
    popq %r8
    
    cmpl $0, %eax
    jle error
    
    cmpl %eax, %r8d
    jg error
    
    cmpl $0, %r8d
    jl error
    
    movl (%rcx), %ebx # HEAD
    movl $0, %r10d # CONTADOR PARA ELEMENTOS REMOVIDOS
    
    subl $1, %esi
    
    cmpl (%rdx), %ebx
    jg tail_first
    
    
loop_head_first:
	
	cmpl %r10d, %r8d
	je loop_end
	
	movl (%rdi, %rbx, 4), %r11d
	movl %r11d, (%r9, %r10, 4)
	
	incl %ebx   #PROXIMO ELEMENTO DO BUFFER
	incl %r10d  #INCREMENTA O CONTADOR  
	
	jmp loop_head_first
	
tail_first:

	cmpl %r8d, %r10d
	je loop_end
	
	movl (%rdi, %rbx, 4), %r11d
	movl %r11d, (%r9, %r10, 4)
	
	cmpl %ebx, %esi
	je head_restart
	
	incl %ebx   #PROXIMO ELEMENTO DO BUFFER
	incl %r10d  #INCREMENTA O CONTADOR
	
	jmp tail_first
	
head_restart:
	
	movl $0, %ebx    #MOVER A HEAD PARA O INICIO
	incl %r10d       #INCREMENTA O CONTADOR 
	
	jmp tail_first

    
loop_end:
	
	cmpl %r8d, %eax
    je end_remove_all

	movl %ebx, (%rcx) #ATUALIZANDO O VALOR DA CABEÇA
	movl $1, %eax     # SUCESSO
	
	ret
	
end_remove_all:
	
	decl %ebx
	movl %ebx, (%rcx) #ATUALIZANDO O VALOR DA CABEÇA
	movl $1, %eax     # SUCESSO
	
	ret
    
error:
	
	movl $0, %eax
	
	ret
