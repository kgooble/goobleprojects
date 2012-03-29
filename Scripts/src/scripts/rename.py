'''
Created on 2011-08-12

@author: Gooble
'''
import os


def rename_files_in_dir(d):
    os.chdir(d)
    
    files = os.listdir()
    
    for f in files:
        prefix = 'E.S. Posthumus - '
        index = f.find(prefix)

        if os.path.isdir(f) and not f.endswith('.ini'):
            rename_files_in_dir(f)
        elif f.endswith('.mp3') and index > 0:
            filename = f
            os.rename(filename, filename[index+len(prefix):])
    
    os.chdir('..')


if __name__ == '__main__':
    os.chdir("/Users/Gooble/Music/E.S. Posthumus")
    dirs = os.listdir()
    for d in dirs:
        if dir(d) and not d.endswith('.ini'):
            rename_files_in_dir(d)