#include <iostream>


int findValueInArray(int value, int arr[], int length){

	int minIndex = 0;
	int maxIndex = length;

	int index = length / 2;
	while (arr[index] != value){
		if (index == minIndex || index == maxIndex)
			return -1;

		std::cout << "at index " << index << " found " << arr[index] << std::endl;
		int temp = index;
		if (arr[index] > value){
			index = index - ((index - minIndex) / 2);
			maxIndex = temp;
		}
		else{  
			index = index + ((maxIndex - index) / 2);
			minIndex = temp;
		}
		
		
	}

	return index;
}


int main(){

	int arr[] = {1, 2, 3, 3, 3, 4, 4, 4, 4, 6, 6, 9, 10, 11, 12, 15, 176};

	int index = findValueInArray(5, arr, sizeof(arr)/sizeof(arr[0]));

	std::cout << "Found 5 in array at index " << index << std::endl;

	system("pause");

	return 0;
}