'''
Created on 2012-03-04

@author: Gooble
'''
import unittest
from test.display.mock.mockgame import MockGame
from test.display.mock.mockthread import MockGameThread
from test.display.mock.mockeventhandler import MockEventHandler
from test.display.mock.mockevent import MockEvent
from main.display.gamestate import GameState
from test.display.mock.mockdisplay import MockDisplay

class Test(unittest.TestCase):
    
    def setUp(self):
        self.game = MockGame()
        self.gamethread = MockGameThread(self.game)
    
    def test_use_eventhandler_and_start_thread_first_time_processing_then_draw_screen(self):
        eventhandler = MockEventHandler(self.game)
        display = MockDisplay(self.game)
        state = GameState(eventhandler, display, self.gamethread)
        
        state.process(MockEvent())
        state.display(None)
        
        self.assertTrue(self.gamethread.started)
        self.assertTrue(eventhandler.processed)
        self.assertTrue(display.displayed)
        
    def test_kill_state_kills_thread(self):
        state = GameState(None, None, self.gamethread)
        
        self.game.set_game_over(True)
        
        state.kill()
        
        self.assertTrue(self.gamethread.gotshutdown)
        self.assertTrue(self.gamethread.gotjoined)

if __name__ == "__main__":
    unittest.main()