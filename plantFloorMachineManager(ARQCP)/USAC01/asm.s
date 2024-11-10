.section .data
    .equ INDICATOR_AND, '&'
    .equ SEPARATOR, '#'
    .equ SIZE_PREFIX_TEMP, 5
    .equ SIZE_PREFIX_HUM, 4

TOKEN_TEMP:
    .asciz "TEMP"

TOKEN_HUM:
    .asciz "HUM"

.section .text
    .global extract_data

# %rdi -- STRING
# %rsi -- TOKEN
# %rdx -- UNIT
# %rcx -- VALUE

extract_data:
    call uppercase_token
    jmp check_token

uppercase_token:
    movq $0, %r10
    loop_upper:
        cmpb $0,(%rsi,%r10,1)
        je exit
        cmpb $97, (%rsi,%r10,1)
        jl skip
        cmpb $122, (%rsi,%r10,1)
        jg skip

        subb $32,(%rsi,%r10,1)
    skip:
        incq %r10
        jmp loop_upper
    exit:
        ret

check_token:
    cmpb $'T', (%rsi)
    je check_token_temp
    cmpb $'H', (%rsi)
    je check_token_hum

    jmp error

check_token_hum:
    leaq TOKEN_HUM(%rip), %r8
    movq $0, %r10
    loop_hum:
        cmpq $SIZE_PREFIX_HUM, %r10
        je valid_token

        movb (%rsi,%r10,1), %bl
        cmpb %bl,(%r8,%r10,1)
        jne error

        incq %r10
        jmp loop_hum

check_token_temp:
    leaq TOKEN_TEMP(%rip), %r8
    movq $0, %r10
    loop_temp:
        cmpq $SIZE_PREFIX_TEMP, %r10
        je valid_token

        movb (%rsi,%r10,1), %bl
        cmpb %bl,(%r8,%r10,1)
        jne error

        incq %r10
        jmp loop_temp







valid_token:
    movq $0, %r10

    call get_informations

    movl $1, %eax
    ret


get_informations:
        
    cmpb $INDICATOR_AND, (%rdi,%r10,1)
    je correct_string

    movb (%rdi,%r10,1), %bl
    cmpb %bl, (%rsi,%r10,1)
    jne get_next_string

    incq %r10
    jmp get_informations


get_next_string:
    cmpb $0,(%rdi)
    je error
    cmpb $'#', (%rdi)
    je prepare_string

    incq %rdi
    jmp get_next_string

prepare_string:
    incq %rdi
    jmp get_informations




correct_string:
    movq $0, %r10

    call get_unit
    call get_value

    ret

get_unit:
    cmpb $INDICATOR_AND, -1(%rdi)
    jne skip_unit
    cmpb $'u', (%rdi)
    jne skip_unit

    addq $5, %rdi

    copy_unit:
        cmpb $INDICATOR_AND, (%rdi,%r10,1)
        je exit

        movb (%rdi,%r10,1), %bl
        movb %bl, (%rdx,%r10,1)
        incq %r10  
        jmp copy_unit
    
    skip_unit:
        addq $1, %rdi
        jmp get_unit
    



error:
    movl $0, %eax
    movb $0, (%rdx)
    movl $0, (%rcx)
    ret



