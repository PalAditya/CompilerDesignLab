//Program for selection sort
#include<stdio.h>
#include<stdlib.h>
int main()
{
	int arr[]={1,-1,9,1,1,2,8,98,22};
	int l=sizeof(arr)/sizeof(arr[0]);
	int i,index,j,max,temp,k;
	for(i=0;i<l;i++)
	{
		index=-1;
		max=arr[l-i-1];
		for(j=0;j<l-i;j++)
		{			
			if(arr[j]>=max)
			{
				max=arr[j];
				index=j;
			}
		}
		if(index<0)
			continue;
		temp=arr[l-i-1];
		arr[l-i-1]=arr[index];
		arr[index]=temp;
	}
	printf("The sorted array is:\n");
	for(i=0;i<l;i++)
		printf("%d ",arr[i]);
	printf("\n");
	return 0;
}