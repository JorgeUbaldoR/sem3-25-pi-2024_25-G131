.section .bss
.global value
	.comm value, 8

.section .text

.global dequeue_value

dequeue_value:
	movl (%rdi), %r12d
	movl %esi, %eax
	decl %eax
	movq $0, %rbx
	cmpl $0, %esi
	je end

fill_values:
	cmpl $0, %eax
	jle restore_values
	movl $0, (%r8, %rbx, 4)
	decl %eax
	incq %rbx
	jmp fill_values

restore_values:
	movl %esi, %eax
	decl %eax
	movq $1, %rbx
	movq $0, %r10

loop:
	cmpl $0, %eax
	jle loop_end
	movl (%rdi,%rbx,4), %r11d
	movl %r11d, (%r8, %r10,4)
	incq %rbx
	incq %r10
	decl %eax
	jmp loop

loop_end:
	movq $0, %rbx
	movl (%r8, %rbx, 4), %r11d
	movl %r11d, (%rcx)
	decq %r10
	movl (%r8, %r10, 4), %r11d
	movl %r11d, (%rdx)
	movl %r12d, (%rdi)
	movl $1, %eax
	ret

end:
	movl $0, %eax
	ret
	
