'''
Created on 2012-02-12

@author: Gooble
'''
from cx_Freeze import setup, Executable

setup(
        name = "GoobleCheckers",
        version = "0.1",
        description = "Checkers made by Gooble",
        executables = [Executable("src/main/startgame.py")])