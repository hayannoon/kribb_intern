# -*- coding: utf-8 -*-
import sys
import os

def search(path,arr):
    output = []
    dirnames = os.listdir(path) #path에 들어있는 디렉터리 이름들 저장
    for dirname in dirnames: #저장된 이름 순회
        fullName = os.path.join(path+'/'+dirname) #경로랑 이름을 더해줘서 디렉터리의 절대경로를 만들어준다.
        if os.path.isdir(fullName): #지금 들어와있는곳이 디렉터리라면
            filenames = os.listdir(fullName) #디렉터리내의 항목들 리스트를 저장하고
            if os.path.isfile(fullName+'/'+str(filenames[0])): #그 항목의 첫번째가 파일이라면
                arr.append(str(dirname) + '\t' + filenames[0] + '\n') #(디렉터리명 탭 파일명 개행)형태로 배열에 String으로 저장


output = [] #배열생성
inputfilepath =str(object=sys.argv[1]) #인자값으로 디렉터리 경로 가져와서
inputfilepath = os.path.abspath(inputfilepath) #절대경로로 만들어준다.
outputfilepath = str(object=sys.argv[2]) #인자값으로 디렉터리 경로 가져와서
outputfilepath = os.path.abspath(outputfilepath) #절대경로로 만들어준다.

search(inputfilepath,output) #정의한 함수 호출

outputfile = open(outputfilepath,'w') #파일 만들고
for item in output:
    outputfile.write(str(item)) #파일에 배열값들 하나하나 쓴다.
outputfile.close()


'''
실행 방법 : 파이썬 파일이 있는 경로로 들어와서 python sample.py "타겟 디렉터리 경로" "output파일 저장할 경로/내가만든이름.txt"
실행 예시 : 파이썬 파일이 있는 경로로 들어와서 python sample.py "C:/users/mydirectory" "C:/users/outputfile.txt"
'''
