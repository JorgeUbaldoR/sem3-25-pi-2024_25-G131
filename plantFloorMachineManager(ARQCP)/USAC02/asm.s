.section .data
    .equ MIN_NUM, 0
    .equ MAX_NUM, 31
    .equ SIZE, 5

.section .text
   .global get_number_binary

get_number_binary:
    # %edi -> Value
    # %rsi -> bits

    movq $0, %r8
    call initialite_zeros

    cmpl $MIN_NUM, %edi
    jl error
    cmpl $MAX_NUM, %edi
    jg error

    
    movl %edi, %eax
    movl $2, %ebx
    movq $SIZE-1, %r8  #Start from end to begining
    jmp get_binary



initialite_zeros:
    cmpq $SIZE, %r8  
    jge exit_zeros

    movb $0,(%rsi,%r8,1)
    incq %r8
    jmp initialite_zeros
    
exit_zeros:
    ret

 
get_binary:
    cmp $0, %eax
    je end_binary

    cdq
    divl %ebx

    test %edx, %edx
    jz skip

    movb $1, (%rsi,%r8,1)

skip:
    decq %r8
    jmp get_binary

end_binary: 
    movl $1, %eax
    ret

error:
    movl $0, %eax
    ret
