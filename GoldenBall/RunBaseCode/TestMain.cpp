#include <SFML/Graphics.hpp>
#include <iostream>

int main(){
	sf::RenderWindow App(sf::VideoMode(800, 600, 32), "SFML Graphics");

	sf::ConvexShape shape;
	shape.SetPointCount(3);
	shape.SetFillColor(sf::Color::Magenta);
	shape.SetPoint(0, sf::Vector2f(10,10));
	shape.SetPoint(1, sf::Vector2f(50, 10));
	shape.SetPoint(2, sf::Vector2f(10, 50));

	while (App.IsOpened()){
		float ElapsedTime = App.GetFrameTime();

		sf::Event Event;
		if (App.PollEvent(Event)){
			if (Event.Type == sf::Event::Closed)
				App.Close();
		}
		App.Clear(sf::Color(200,0,0));
		App.Draw(shape);
		App.Display();
	}

	return EXIT_SUCCESS;
}