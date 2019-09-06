	.file	"lab8.c"
	.def	___main;	.scl	2;	.type	32;	.endef
	.section .rdata,"dr"
LC0:
	.ascii "The sorted array is:\0"
LC1:
	.ascii "%d \0"
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB14:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	subl	$80, %esp
	call	___main
	movl	$1, 20(%esp)
	movl	$-1, 24(%esp)
	movl	$9, 28(%esp)
	movl	$1, 32(%esp)
	movl	$1, 36(%esp)
	movl	$2, 40(%esp)
	movl	$8, 44(%esp)
	movl	$98, 48(%esp)
	movl	$22, 52(%esp)
	movl	$9, 60(%esp)
	movl	$0, 76(%esp)
	jmp	L2
L8:
	movl	$-1, 72(%esp)
	movl	76(%esp), %eax
	movl	60(%esp), %edx
	subl	%eax, %edx
	movl	%edx, %eax
	subl	$1, %eax
	movl	20(%esp,%eax,4), %eax
	movl	%eax, 64(%esp)
	movl	$0, 68(%esp)
	jmp	L3
L5:
	movl	68(%esp), %eax
	movl	20(%esp,%eax,4), %eax
	cmpl	64(%esp), %eax
	jl	L4
	movl	68(%esp), %eax
	movl	20(%esp,%eax,4), %eax
	movl	%eax, 64(%esp)
	movl	68(%esp), %eax
	movl	%eax, 72(%esp)
L4:
	addl	$1, 68(%esp)
L3:
	movl	76(%esp), %eax
	movl	60(%esp), %edx
	subl	%eax, %edx
	movl	%edx, %eax
	cmpl	68(%esp), %eax
	jg	L5
	cmpl	$0, 72(%esp)
	jns	L6
	jmp	L7
L6:
	movl	76(%esp), %eax
	movl	60(%esp), %edx
	subl	%eax, %edx
	movl	%edx, %eax
	subl	$1, %eax
	movl	20(%esp,%eax,4), %eax
	movl	%eax, 56(%esp)
	movl	76(%esp), %eax
	movl	60(%esp), %edx
	subl	%eax, %edx
	movl	%edx, %eax
	leal	-1(%eax), %edx
	movl	72(%esp), %eax
	movl	20(%esp,%eax,4), %eax
	movl	%eax, 20(%esp,%edx,4)
	movl	72(%esp), %eax
	movl	56(%esp), %edx
	movl	%edx, 20(%esp,%eax,4)
L7:
	addl	$1, 76(%esp)
L2:
	movl	76(%esp), %eax
	cmpl	60(%esp), %eax
	jl	L8
	movl	$LC0, (%esp)
	call	_puts
	movl	$0, 76(%esp)
	jmp	L9
L10:
	movl	76(%esp), %eax
	movl	20(%esp,%eax,4), %eax
	movl	%eax, 4(%esp)
	movl	$LC1, (%esp)
	call	_printf
	addl	$1, 76(%esp)
L9:
	movl	76(%esp), %eax
	cmpl	60(%esp), %eax
	jl	L10
	movl	$10, (%esp)
	call	_putchar
	movl	$0, %eax
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE14:
	.ident	"GCC: (GNU) 4.8.1"
	.def	_puts;	.scl	2;	.type	32;	.endef
	.def	_printf;	.scl	2;	.type	32;	.endef
	.def	_putchar;	.scl	2;	.type	32;	.endef