.section .data
    .equ SEPARATOR, '&'
    .equ VALUE_PREFIX, "value:"
    .equ UNIT_PREFIX, "unit:"
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

    jmp error_token

check_token_hum:
    leaq TOKEN_HUM(%rip), %r8
    movq $0, %r10
    loop_hum:
        cmpq $SIZE_PREFIX_HUM, %r10
        je end_string

        movb (%rsi,%r10,1), %bl
        cmpb %bl,(%r8,%r10,1)
        jne error_token

        incq %r10
        jmp loop_hum

check_token_temp:
    leaq TOKEN_TEMP(%rip), %r8
    movq $0, %r10
    loop_temp:
        cmpq $SIZE_PREFIX_TEMP, %r10
        je end_string

        movb (%rsi,%r10,1), %bl
        cmpb %bl,(%r8,%r10,1)
        jne error_token

        incq %r10
        jmp loop_temp

end_string:
    movl $1, %eax
    ret

error_token:
    movl $0, %eax
    movb $0, (%rdx)
    movl $0, (%rcx)
    ret