.section .text

.global get_n_element

get_n_element:
	cmpq %rdx, %rcx
	je empty
	movl $0, %ebx
loop:
	cmpq %rdx, %rcx
	je not_empty
	addq $4, %rcx
	incl %ebx
	jmp loop
	
empty:
	movl $0, %eax
	ret
	
not_empty:
	movl %ebx, %eax
	ret
