'''
Created on 2012-03-10

@author: Gooble
'''
import unittest
from test.display.mock.mockevent import MockEvent
import pygame
from main.display.quitplushandler import QuitPlusHandler


class Test(unittest.TestCase):

    def test_raise_system_exit_when_sent_exit(self):
        handler = QuitPlusHandler(None)
        event = MockEvent(type=pygame.QUIT)
        try:
            handler.process(event)
            self.fail("shouldn't get here")
        except SystemExit:
            pass


if __name__ == "__main__":
    unittest.main()