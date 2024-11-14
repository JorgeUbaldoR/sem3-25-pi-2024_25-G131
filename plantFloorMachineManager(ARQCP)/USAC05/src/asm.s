.section .text
    .global enqueue_value

enqueue_value:
    movq %rdi, %rax         # Buffer base address in %rax
    movl %esi, %r9d         # Length of buffer in %r9d
    movl (%rdx), %r10d      # Current tail position in %r10d
    movl (%rcx), %r11d      # Current head position in %r11d

    movl %r11d, %r12d       # Copy head to %r12d for incrementing
    addl $1, %r12d          # Increment the head position for check
    cmpl %r9d, %r12d        # Check if incremented head equals length
    jl head_wrap_check     # If not, continue
    movl $0, %r12d          # Wrap head to 0 if it reached length

head_wrap_check:
    addl $1, %r10d               # Increment tail
    cmpl %r9d, %r10d             # Check if tail reached the end
    jl tail_update_done         # If not, continue
    movl %r8d, (%rax, %r10, 4)  # Insert the value at buffer[tail]
    movl $0, %r10d               # Wrap tail to 0 if it reached length

tail_update_done:
	movl %r8d, (%rax, %r10, 4)  # Insert the value at buffer[tail]
    movl %r10d, (%rdx)           # Store new tail position
    movl %r12d, (%rcx)           # Store incremented head position
    cmpl %r11d, %r10d           # Compare head with incremented tail
    je buffer_full              # If equal, buffer is full, return 0
    movl $1, %eax                # Return 1 to indicate success
    ret

buffer_full:
    movl $0, %eax                # Return 0 to indicate buffer is full
    ret
