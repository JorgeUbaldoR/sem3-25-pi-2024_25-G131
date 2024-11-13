.section .text
.global dequeue_value

dequeue_value:
    cmpq %rdx, %rcx
    je empty

  
    movl (%rcx), %eax       
    movl %eax, (%r8)

    
shift_loop:
    movl 4(%rdi), %eax      
    movl %eax, (%rdi)       
    addq $4, %rdi           
    cmpq %rdi, (%rdx)         
    jne shift_loop

    
    movl $0, (%rdx)
    #subq $4, %rdx
    movq %rdi, (%rdx)

    movl $1, %eax
    ret

empty:
    movl $0, %eax
    ret
