'''
Created on 2012-02-01

@author: Gooble
'''
from main.movement.transform.point import Point
from main.shapes.spawn.oneshapespawn import OneShapeSpawn
from main.shapes.tile import T_TILE
import unittest

class Test(unittest.TestCase):

    def test_spawn_centered_shape(self):
        spawner = OneShapeSpawn(T_TILE)
        shape = spawner.get_next_centered_shape(10)
        self.assertEqual([Point(0, 5),
                          Point(0, 4),
                          Point(-1, 5),
                          Point(0, 6)], shape.get_points())
        

if __name__ == "__main__":
    unittest.main()