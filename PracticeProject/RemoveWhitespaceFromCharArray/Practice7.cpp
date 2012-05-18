#include <iostream>

char* escape(char* str){
	int current = 0;
    int ahead = 0;
 
    while (str[ahead] != '\0') {
        while (str[ahead] == ' ') ahead++;
 
        str[current] = str[ahead];
        current++;
        ahead++;
    }
 
    // Terminate the string with a null char at the new ending.
    str[current] = '\0';
	return str;
}

int main(){
	char g[] = "  hello ";

	escape(g);

	int i = 0;
	while (g[i] != '\0'){
		std::cout << g[i];
		i++;
	}

	std::cout << std::endl;

	system("pause");
	return 0;
}