'''
Created on 2012-02-26

@author: Gooble
'''
from main.view.drawing.drawablethinking import DrawableThinkingText, DrawableText
from main.view.drawing.colours import Colours
from main.view.drawing.drawable import DrawableKing, DrawableBackground, DrawablePiece
from main.view.dimensions import DEFAULT_HEIGHT, DEFAULT_WIDTH

class Drawables(object):
    
    def __init__(self, game):
        self.game = game
        self.tile_width = game.get_tile_width()
        self.tile_height = game.get_tile_height()

    def create(self, position):
        drawables = []
        held_drawable = self.create_all_and_get_held(position, drawables)
        if held_drawable is not None:
            drawables.append(held_drawable)
        if self.game.is_game_over():
            drawables.append(DrawableText("Game Over", 25, DEFAULT_WIDTH/4, DEFAULT_HEIGHT/2))
        elif self.game.is_computers_turn():
            drawables.append(DrawableThinkingText())
        return drawables
    
    def create_all_and_get_held(self, position, drawables):
        held_drawable = None
        for row in range(0, self.game.get_num_rows()):
            for col in range(0, self.game.get_num_cols()):
                piece = self.game.get_piece(row, col)
                colour = Colours().piece(piece)
                x = col * self.tile_width
                y = row * self.tile_height
                drawables.append(self.get_background_drawable(x, y, row, col))
                if self.currently_holding_this_piece(position, row, col):
                    held_drawable = self.get_held_drawable(position, piece, colour)
                elif self.there_is_a_piece_here(row, col, piece):
                    drawables.append(self.get_current_drawable_piece(x, y, colour, piece))
        return held_drawable
        
    def get_held_drawable(self, position, piece, colour):
        x = int(position[0] - self.tile_width / 2)
        y = int(position[1] - self.tile_height / 2)
        return self.get_current_drawable_piece(x, y, colour, piece)

    def get_current_drawable_piece(self, x, y, colour, piece):
        if piece is not None and piece.is_king():
            return DrawableKing(x, y, self.tile_width, self.tile_height, colour)
        else:
            return DrawablePiece(x, y, self.tile_width, self.tile_height, colour)

    def get_background_drawable(self, x, y, row, col):
        bgcolour = Colours().background(row, col)
        return DrawableBackground(x, y, self.tile_width, self.tile_height, bgcolour)
    
    def there_is_a_piece_here(self, row, col, piece):
        return not self.game.is_holding_piece(row, col) and piece is not None

    def currently_holding_this_piece(self, position, row, col):
        return self.game.is_holding_piece(row, col) and position is not None and self.game.get_piece(row, col) is not None
