.section .text
.global get_n_element

get_n_element:
	cmpl $0, (%rdx)
	jl fail
	cmpl $0, (%rcx)
	jl fail
	cmpl $1, %esi
	je check_head_tail
continue:
	cmpl %esi, (%rdx)
	jg fail
	
	cmpl %esi, (%rcx)
	jg fail
	
	movl $0, %eax
	movl (%rdx), %r8d
	cmpl %r8d, (%rcx)
	je is_empty
	
	incl %r8d
	cmpl %r8d, (%rcx)
	je is_full

	movl (%rcx), %r8d
	cmpl (%rdx), %r8d
	jg tail_first

head_first:
	cmpl (%rdx), %r8d
	jg end
	incl %eax
	incl %r8d
	jmp head_first

tail_first:
	subl %r8d, %esi
	addl (%rdx), %esi
	movl %esi, %eax
	incl %eax
	ret
	
	
is_empty:
	movl $0, %eax
	ret

is_full:
	movl %esi, %eax
	ret

check_head_tail:
	movl (%rdx), %r13d
	cmpl %r13d, (%rcx)
	jne continue
	movl $1, %eax

end:
	ret

fail:
	movl $-1, %eax
	ret
