'''
Created on 2012-03-10

@author: Gooble
'''
import unittest
from test.display.mock.mockevent import MockEvent
import pygame
from main.display.generaleventshandler import GeneralEventsHandler


class Test(unittest.TestCase):

    def test_raise_system_exit_when_sent_exit(self):
        handler = GeneralEventsHandler()
        event = MockEvent(type=pygame.QUIT)
        try:
            handler.process_general(event, {})
            self.fail("shouldn't get here")
        except SystemExit:
            pass


if __name__ == "__main__":
    unittest.main()