'''
Created on 2012-02-01

@author: Gooble
'''
import unittest
from main.movement.direction import LEFT, RIGHT, UP, DOWN
from main.movement.edgesfrompieces import EdgesFromPieces

class Test(unittest.TestCase):

    def test_get_edges_from_pieces(self):
        pieces = []
        for i in range(0, 5):
            pieces.append([i,i,i,i])
        
        edges = EdgesFromPieces(pieces).create_edges()
        self.assertEqual(0, edges.get_edge(LEFT))
        self.assertEqual(3, edges.get_edge(RIGHT))
        self.assertEqual(0, edges.get_edge(UP))
        self.assertEqual(4, edges.get_edge(DOWN))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.test_get_edges_from_pieces']
    unittest.main()