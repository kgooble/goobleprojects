'''
Created on 2011-12-22

@author: Gooble
'''

from main.ai.maxmove import MaxMove
from main.ai.originmoves import OriginMoves
from main.game import turn
import copy

class BestMoveWithDepth(object):
    
    def __init__(self, depth, origin):
        self.depth = depth
        self.origin = origin
        
    def calculate_for(self, game):
        best_move, best_score = self._get_best_move(game, self.depth, None)
        if best_move is None:
            raise NameError('must provide a minimum depth of 1')
        return best_move

    def _get_best_move(self, game, cur_depth, last_move):
        if cur_depth == 0:
            return last_move, 0
        
        all_moves = OriginMoves(game.board, self.origin).get_moves()
        game_copy = copy.deepcopy(game)
        best_move = None
        best_score = 0
        for move in all_moves:
            score = self._get_own_score(game_copy, move)
            score -= self._get_enemy_score(game_copy)
            
            best_sub_move, best_sub_score = self._get_best_move(game_copy, cur_depth - 1, move)
            score += best_sub_score
            
            if score > best_score or best_move is None:
                best_score = score
                best_move = move
            
        return best_move, best_score
    
    
    def _get_enemy_score(self, game_copy):
        enemy_moves = OriginMoves(game_copy.board, turn.other_origin(self.origin)).get_moves()
        max_enemy_move = MaxMove().get_max_move(enemy_moves)
        if max_enemy_move is None:
            return -1
        game_copy.make_move(max_enemy_move)
        return max_enemy_move.get_pieces_eaten()


    def _get_own_score(self, game_copy, move):
        game_copy.make_move(move)
        score = move.get_pieces_eaten()
        return score