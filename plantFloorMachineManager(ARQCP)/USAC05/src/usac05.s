.section .text
    .global enqueue_value

enqueue_value:
    movq %rdi, %rax         # Buffer base address in %rax
    movl %esi, %r9d         # Length of buffer in %r9d
    movl (%rdx), %r10d      # Current tail position in %r10d
    movl (%rcx), %r11d      # Current head position in %r11d
    
    cmpl %r10d, %r11d       # Compare both head and tail to see if the buffer is empty
    je empty_buffer         # If equal, buffer is empty

    movl %r10d, %r12d       # Copy tail to %r12d
    addl $1, %r12d          # Increment tail position
    cmpl %r9d, %r12d        # Check if it exceeds buffer length
    jl tail_wrap_check      # If not, continue
    movl $0, %r12d          # Wrap tail to 0 if it reached length

tail_wrap_check:

    movl %r8d, (%rax, %r12, 4)  # Insert value at buffer[new tail]
    movl %r12d, (%rdx)          # Update tail position
    movl %r11d, (%rcx)          # Keep head unchanged
    subl $1, %r9d               # Subtract 1 from length
    cmpl %r12d, %r9d            # If it is equal to my position means that it got to he last position (buffer is full)
    je ending
    cmpl %r12d, %r11d           # Compare incremented tail with head
    je buffer_full              # If equal, buffer is full
    movl %r11d, %r15d           # Copy head to %r15d
    subl $1, %r15d              # Subtract 1 from head
    cmpl %r12d, %r15d           # Compare if head and tail are equal
    je end
    movl $0, %eax               # Return 0
    ret

buffer_full:
    addl $1, %r11d              # Increment head position
    cmpl %r9d, %r11d            # Check if head exceeds buffer length
    jl head_wrap_check          # If not, continue
    movl $0, %r11d              # Wrap head to 0 if it reached length

head_wrap_check:
    movl %r8d, (%rax, %r12, 4)  # Insert value at buffer[new tail]
    movl %r12d, (%rdx)          # Update tail position
    addl $1, %r12d              # Increment tail position
    cmpl %r12d, %r11d           # Compare incremented tail with head
    je end                      # If equal, buffer is full
    movl %r11d, (%rcx)          # Update head position
    movl $0, %eax               # Return 0
    ret

end:
	movl %r11d, (%rcx)          # Update head position
	movl $1, %eax               # Return 1
	ret

empty_buffer:
    addl $1, %r10d              # Increment tail index
    movl %r10d, (%rdx)          # Update tail position
    movl %r8d, (%rax, %r10, 4)  # Insert value at buffer[tail]
	movl $0, %eax               # Return 0
	ret

ending:
	movl %r12d, (%rdx)          # Update tail position
	jmp end
