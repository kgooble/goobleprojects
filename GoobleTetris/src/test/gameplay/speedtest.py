'''
Created on 2012-02-04

@author: Gooble
'''
import unittest
from main.gameplay.speed import Speed

class Test(unittest.TestCase):

    def test_speed_at_level_1_is_slow(self):
        speed = Speed().for_score(0)
        self.assertEqual(48, speed)
        
    def test_speed_for_higher_score_is_faster(self):
        self.assertEqual(46, Speed().for_score(5000))
        self.assertEqual(44, Speed().for_score(10000))
        self.assertEqual(42, Speed().for_score(15000))
        self.assertEqual(40, Speed().for_score(20000))
        self.assertEqual(10, Speed().for_score(1000000))
        self.assertEqual(10, Speed().for_score(2000000))
        self.assertEqual(10, Speed().for_score(5000000))
  
    def test_speed_for_level_plus_score(self):
        self.assertEqual(48, Speed().for_start_level_and_curr_score(1, 3000))
        self.assertEqual(46, Speed().for_start_level_and_curr_score(2, 0))
        self.assertEqual(44, Speed().for_start_level_and_curr_score(2, 7500))
        self.assertEqual(44, Speed().for_start_level_and_curr_score(3, 0))
        self.assertEqual(42, Speed().for_start_level_and_curr_score(4, 0))
        


if __name__ == "__main__":
    unittest.main()