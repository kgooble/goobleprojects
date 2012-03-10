'''
Created on 2012-03-04

@author: Gooble
'''
import unittest
import pygame
from test.display.mock.mockgame import MockGame
from test.display.mock.mockevent import MockEvent
from main.display.game.gameeventhandler import GameEventHandler

class Test(unittest.TestCase):

    def setUp(self):
        self.game = MockGame()
        self.handler = GameEventHandler(self.game)

    def test_move_left_when_sent_left_key(self):
        event = MockEvent(key=pygame.K_LEFT, type=pygame.KEYDOWN)
        self.handler.process(event)
        self.assertEqual(-1, self.game.position)
        
    def test_move_right_when_sent_right_key(self):
        event = MockEvent(key=pygame.K_RIGHT, type=pygame.KEYDOWN)
        self.handler.process(event)
        self.assertEqual(1, self.game.position)
        
    def test_speedup_when_sent_down_key_pressed(self):
        event = MockEvent(key=pygame.K_DOWN, type=pygame.KEYDOWN)
        self.handler.process(event)
        self.assertTrue(self.game.goingfast)
        
    def test_slowdown_when_sent_down_key_released(self):
        event = MockEvent(key=pygame.K_DOWN, type=pygame.KEYDOWN)
        self.handler.process(event)
        self.assertTrue(self.game.goingfast)
        
        event = MockEvent(key=pygame.K_DOWN, type=pygame.KEYUP)
        self.handler.process(event)
        self.assertFalse(self.game.goingfast)
        
    def test_rotate_on_up_key_pressed(self):
        event = MockEvent(key=pygame.K_UP, type=pygame.KEYDOWN)
        self.handler.process(event)
        self.assertTrue(self.game.rotated)
        
    def test_drop_when_spacebar_pressed(self):
        event = MockEvent(key=pygame.K_SPACE, type=pygame.KEYDOWN)
        self.handler.process(event)
        self.assertTrue(self.game.dropped)

if __name__ == "__main__":
    unittest.main()