'''
Created on 2012-02-12

@author: Gooble
'''

from cx_Freeze import setup, Executable

setup(
        name = "GoobleTetris",
        version = "0.1",
        description = "Tetris made by Gooble",
        executables = [Executable("src/main/tetris.py")])