.section .data

.section .text
   .global median  
   .global sort_array

# %rdi -- vec 
# %esi -- length
# %rdx -- me

median:
   movq %rdx, %r15
   jmp check_length

check_length:
   cmpl $0, %esi
   jle error

   movb $'1', %dl
   call sort_array

   cmpl $0, %eax
   je error

   jmp get_median
    
get_median:
   movl $2, %ebx
   movl %esi, %eax
   
   cdq
   divl %ebx
   
   movslq %eax, %r8
   cmpl $0, %edx
   je length_even

   movl (%rdi,%r8,4), %ebx
   movl %ebx, (%r15)
   
   jmp success

length_even:
   movl (%rdi,%r8,4), %eax
   addl -4(%rdi,%r8,4), %eax

   cdq 
   idivl %ebx
   movl %eax, (%r15)
   jmp success

success:
   movl $1, %eax
   ret

error:
   movl $0, %eax
   ret