#include <iostream>

class ObjWithId{
private:
	int id;
public:
	ObjWithId(int idVal){
		id = idVal;
	}
	int getId(){
		return id;
	}
};

void sort(ObjWithId arr[], int length){
	for (int i = 0; i < length; i++){
		while (arr[i].getId() != i){
			ObjWithId temp = arr[arr[i].getId()];
			arr[arr[i].getId()] = arr[i];
			arr[i] = temp;
		}
	}
}


int main(){

	ObjWithId arr[] = {ObjWithId(3), ObjWithId(1), ObjWithId(2), ObjWithId(0)};

	sort(arr, 4);

	for (int i =0; i < 4; i++){
		std::cout << "At positon i, obj with id " << arr[i].getId() << std::endl;
	}

	system("pause");
	return 0;
}