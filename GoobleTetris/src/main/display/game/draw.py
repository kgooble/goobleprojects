'''
Created on 2012-02-04

@author: Gooble
'''

import pygame
from main.display.game.tilecolors import WHITE, TILE_COLORS
from main.display.game.screencoords import ScreenCoords
from main.shapes.shape import Shape
from main.movement.transform.translation import Translation
from main.movement.direction import DOWN
from main.display.drawing.factory import DrawableFactory
from main.display.drawing.centerall import CenterAll
from main.display.center import Center

PADDING = 20

class DrawBoard(object):
    def __init__(self, game, predicting=False):
        self.game = game
        self.predicting = predicting

    def display(self, screen):
        self.draw_board(screen)
        self.write_score(screen)
        self.draw_prediction(screen)
        self.draw_next_shape(screen)

    def write_score(self, screen):
        pygame.draw.line(screen, WHITE, [screen.get_height()/2, 0], [screen.get_height()/2, 600], 5)
        texts = [DrawableFactory().create_text("Score", screen.get_height()/2 + PADDING, 200),
        DrawableFactory().create_text(str(self.game.get_score()), screen.get_height()/2 + PADDING, 250)]
        CenterAll(texts, x_offset=screen.get_height()/2).\
                  in_screen_sized(screen.get_width()-screen.get_height()/2, screen.get_height())
        [text.draw(screen) for text in texts]
    
    def fade(self, color):
        new_color = [color[0] - 200, color[1] - 200, color[2] - 200]
        for i in range (0, len(new_color)):
            if new_color[i] < 0:
                new_color[i] = 0
        return new_color
    
    def draw_piece(self, screen, coords, piece, faded=False):
        color = TILE_COLORS[piece]
        if faded:
            color = self.fade(color)
        DrawableFactory().create_piece(coords, color, piece.is_empty(), faded=faded).draw(screen)
                          
    def draw_prediction(self, screen):
        cur_shape = self.game.get_current_shape()
        if cur_shape is None or not self.predicting:
            return
        predicted = Shape(cur_shape.get_tile())
        predicted.set_position(self.game.get_predicted_points())
        self.draw_shape(screen, predicted, faded=True)
                    
    def draw_board(self, screen):
        pieces = self.game.get_pieces()
        for i in range(0, len(pieces)):
            for j in range(0, len(pieces[0])):
                piece = self.game.get_pieces()[i][j]
                coords = ScreenCoords(row=i, col=j, tile_width=(screen.get_height()/20))
                self.draw_piece(screen, coords, piece)
                
    def draw_next_shape(self, screen):
        next_shape = Shape(self.game.get_next_shape())
        for i in range(0, 2):
            next_shape.set_position(Translation().in_direction(next_shape.get_points(), DOWN))
        pieces=[]
        color = TILE_COLORS[next_shape.get_tile()]
        for point in next_shape.get_points():
            coords = ScreenCoords(row=point.row, col=point.col, tile_width=(screen.get_height()/20))
            pieces.append(DrawableFactory().create_piece(coords, color, next_shape.get_tile().is_empty()))
            
        Center(start=screen.get_height()/2, end=screen.get_width()).pieces_along_x(pieces)
        for piece in pieces:
            piece.draw(screen)
    
    def draw_shape(self, screen, shape, faded=False):
        for point in shape.get_points():
            coords = ScreenCoords(row=point.row, col=point.col, tile_width=(screen.get_height()/20))
            self.draw_piece(screen, coords, shape.get_tile(), faded=faded)
        
        