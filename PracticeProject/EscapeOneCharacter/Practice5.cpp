#include <iostream>
#include <string>


std::string escape(std::string str){
	std::string escaped = "";
	for (unsigned int i = 0; i < str.length(); i++){
		if (str[i] == '%'){
			escaped.append("%");
		}
		escaped.push_back(str[i] );
	}
	return escaped;
}

int main(){

	std::string ans = escape("Hello%MyName%isBob");

	printf("%s \n", ans.c_str());

	system("pause");
	return 0;

}