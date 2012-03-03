'''
Created on 2011-12-26

@author: Gooble
'''
from main.ai.originmoves import OriginMoves
import copy
from main.game import turn

LOSS = -1000
WIN = +1000

class MinimaxMove(object):

    def __init__(self, depth, origin):
        self.depth = depth
        self.origin = origin
        
    def calculate_for(self, game):
        best_move, best_score = self._get_best_move(game, self.depth, None, LOSS, WIN)
        if best_move is None:
            raise NameError('must provide a minimum depth of 1')
        return best_move

    def _score_should_be_replaced(self, best_score, score, game):
        if game.is_current_turn(self.origin):
            return score > best_score
        return score < best_score
    
    def _initialize_max_score(self, game, minimum, maximum):
        if game.is_current_turn(self.origin):
            return minimum
        return maximum
        
    def _get_all_moves(self, game):
        return OriginMoves(game.board, game.current_turn.origin).get_moves()
    
    def _get_best_move(self, game, cur_depth, last_move, minimum, maximum):
        if cur_depth == 0:
            return last_move, self._eval(game)
        best_score = self._initialize_max_score(game, minimum, maximum)
        best_move = None
        all_moves = self._get_all_moves(game)
        for move in all_moves:
            game_copy = copy.deepcopy(game)
            game_copy.make_move(move)
            sub_move, score = self._get_best_move(game_copy, cur_depth - 1, move,\
                                                  self._get_min(game, minimum, best_score),\
                                                  self._get_max(game, maximum, best_score))
            if self._score_should_be_replaced(best_score, score, game):
                best_score = score
                best_move = move
                if self._score_out_of_valid_range(game, best_score, minimum, maximum):
                    return best_move, best_score
        return best_move, best_score

    def _get_min(self, game, minimum, score):
        if game.is_current_turn(self.origin):
            return score
        return minimum
    
    def _get_max(self, game, maximum, score):
        if game.is_current_turn(self.origin):
            return maximum
        return score
        
    def _score_out_of_valid_range(self, game, best_score, minimum, maximum):
        return (game.is_current_turn(self.origin) and best_score > maximum) \
            or (not game.is_current_turn(self.origin) and best_score < minimum) 
        
    def _eval(self, game):
        return game.get_score(self.origin) - game.get_score(turn.other_origin(self.origin))
        
    def _is_my_turn(self, game):
        return game.is_current_turn(self.origin)