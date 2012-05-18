#include <iostream>
#include <string>

int findSubstringIn(std::string needle, std::string haystack){
	for (int i = 0; i < haystack.length() - needle.length(); i++){
		bool found = true;
		
		for (int j = i; j < needle.length() + i; j++){
			if (needle[j-i] != haystack[j]){
				found = false;
				break;
			}
		}

		if (found)
			return i;

	}

	return -1;
}


int main(){
	int index = findSubstringIn("sub", "I like subway");

	printf("Found at index %d\n", index);

	system("pause");
	return 0;
}