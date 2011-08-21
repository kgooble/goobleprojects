'''
Created on 2011-08-19

@author: Gooble
'''

import os


def check_duplicates(i, pics1, pics2):
    duplicates = []
    for pic1 in pics1:
        for pic2 in pics2:
            if pic1 == pic2 and (pic2.endswith('JPG') or pic2.endswith('MOV')):
                duplicates.append(pic2)
                
    return duplicates      


if __name__ == '__main__':
    os.chdir('/Users/Gooble/Pictures')
    pics = {}
    dirs = os.listdir()
    for folder in dirs:
        if os.path.isdir(folder):
            os.chdir(folder)
            pics[folder] = os.listdir()
            os.chdir('..')
            
            
    # now we have a dictionary full of folder->pic lists
    # for each pic list, check with all other pic lists if there's a pic with the same name
    # if there is, add the dir & pic to the to-delete list
    keys = list(pics.keys())
    dups = {}
    for i in range(0, len(keys) - 1):
        dups[keys[i]] = []
        for j in range(i+1, len(keys)):
            dups[keys[i]].extend(check_duplicates(i, pics[keys[i]], pics[keys[j]]))
        
    
    for folder, pics in dups.items():
        os.chdir(folder)
        for pic in pics:
            os.remove(pic)
        os.chdir('..')
    