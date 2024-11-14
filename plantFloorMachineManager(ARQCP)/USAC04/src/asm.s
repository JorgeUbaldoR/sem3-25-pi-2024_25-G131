.section .data

.section .text
    .global format_command

format_command:
    movq %rdi, %rbx
    movq %rdi, %rcx

trim_start:
    movb (%rcx), %al
    cmpb $0, %al
    je end
    cmpb $' ', %al
    je skip_start

copy_characters:
    movb (%rcx), %al
    cmpb $0, %al
    je trim_end
    movb %al, (%rbx)
    addq $1, %rbx
    addq $1, %rcx
    jmp copy_characters

skip_start:
    addq $1, %rcx
    jmp trim_start

trim_end:
    subq $1, %rbx

trim_check:
    cmpb $' ', (%rbx)
    jne end_with_null
    subq $1, %rbx
    jmp trim_check

end_with_null:
    addq $1, %rbx
    movb $0, (%rbx)
    movq %rdi, %rcx
    movb (%rcx), %al
    cmpb $'o', %al
    je check_second_char
    cmpb $'O', %al
    jne not_match

check_second_char:
    addq $1, %rcx
    movb (%rcx), %al
    cmpb $'p', %al
    je match_op
    cmpb $'P', %al
    je match_op
    cmpb $'n', %al
    je match_on
    cmpb $'N', %al
    je match_on
    cmpb $'f', %al
    je match_off
    cmpb $'F', %al
    jne not_match

not_match:
    movb $0, (%rdx)
    movl $0, %eax
    ret

match_op:
    movb $'O', (%rdx)
    addq $1, %rdx
    movb $'P', (%rdx)
    addq $1, %rdx
    jmp append_prefix

match_on:
    movb $'O', (%rdx)
    addq $1, %rdx
    movb $'N', (%rdx)
    addq $1, %rdx
    jmp append_prefix

match_off:
    movb $'O', (%rdx)
    addq $1, %rdx
    movb $'F', (%rdx)
    addq $1, %rdx
    movb $'F', (%rdx)
    addq $1, %rdx
    jmp append_prefix

append_prefix:
    movb $' ', (%rdx)
    addq $1, %rdx
    movb $',', (%rdx)
    addq $1, %rdx
    movb $' ', (%rdx)
    addq $1, %rdx
    jmp binary

binary:
    movq %rsi, %rax
    movq $5, %r8
    movq %rdx, %rbx
    movq $2, %r11

convert_bit:
    xorq %rdx, %rdx
    divq %r11
    addb $'0', %dl
    movb %dl, (%rbx)
    addq $1, %rbx
    decq %r8
    jz finish_binary
    movb $',', (%rbx)
    addq $1, %rbx
    movb $' ', (%rbx)
    addq $1, %rbx
    movq %rax, %rsi
    jmp convert_bit

finish_binary:
    movb $0, (%rbx)
    movl $1, %eax
    ret

end:
    movl $0, %eax
    ret
