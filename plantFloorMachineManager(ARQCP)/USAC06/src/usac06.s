.section .text
.global dequeue_value

dequeue_value:
	pushq %r8
	pushq %rsi
    call get_n_element
    popq %rsi
    popq %r8
	
    cmpl $0, %eax
    jle fail
    
    movl (%rcx), %ebx
	incl %ebx
    cmpl %esi, %ebx
    je head_at_end
	


move_head:
	decl %ebx
	movslq %ebx, %rbx
	movl (%rdi, %rbx, 4), %r9d
	movl %r9d, (%r8)
	incl (%rcx)
	movl $1, %eax
	ret

head_at_end:
	decl %ebx
	movslq %ebx, %rbx
	movl (%rdi, %rbx, 4), %r9d
	movl %r9d, (%r8)
	movq $0, %rbx
	movl (%rdi, %rbx, 4), %r9d
	movl %r9d, (%rcx)
	movl $1, %eax
	ret
	
fail:
	ret

