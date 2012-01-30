'''
Created on 2012-01-29

@author: Gooble
'''
import unittest
import main.config

class Test(unittest.TestCase):

    def test_create_config_produces_correct_tile_set(self):
        config = main.config.Configuration().create(
                 ["0 0 0 0 0",
                  "0 0 0 0 0",
                  "1 0 1 0 1"])
        
        for row in range(0, 2):
            for col in range (0, 5):
                self.assertTrue(config[row][col].is_empty())
                
        self.assertFalse(config[2][0].is_empty())
        self.assertTrue(config[2][1].is_empty())
        self.assertFalse(config[2][2].is_empty())
        self.assertTrue(config[2][3].is_empty())
        self.assertFalse(config[2][4].is_empty())


if __name__ == "__main__":
    unittest.main()