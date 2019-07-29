# -*- coding:utf-8 -*-
from pptx import Presentation
from PyQt5.QtWidgets import (QApplication, QComboBox, QVBoxLayout,QWidget, QPushButton, QDesktopWidget,QGridLayout, QLabel, QLineEdit, QTextEdit)
from PyQt5.QtGui import QIcon, QFont
from PyQt5.QtCore import QCoreApplication
import os
import sys
import io
import copy
import makePPT_V2

sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')

words_array = ['창세기','출애굽기','레위기','민수기','신명기','여호수아']
cb_value = ""
start_value = ""
chapter_value = ""
end_value = ""
title_value = ""
class MyApp(QWidget):


    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):

        self.lbl = QLabel('',self)
        self.lbl.move(0,0)
        cb = QComboBox(self)
        cb.addItems(words_array)

        chapter = QLineEdit(self)
        start = QLineEdit(self)
        end = QLineEdit(self)
        title = QLineEdit(self)

        grid = QGridLayout()
        self.setLayout(grid)

        grid.addWidget(QLabel('말씀:'), 0, 0)
        grid.addWidget(QLabel('장:'), 1, 0)
        grid.addWidget(QLabel('시작 절:'), 2, 0)
        grid.addWidget(QLabel('끝 절:'), 3, 0)
        grid.addWidget(QLabel('제목:'), 4, 0)


        grid.addWidget(cb, 0, 1)
        grid.addWidget(chapter, 1, 1)
        grid.addWidget(start, 2, 1)
        grid.addWidget(end, 3, 1)
        grid.addWidget(title, 4, 1)

        cb.activated[str].connect(self.onActivated)

        btn = QPushButton('Start', self)
        btn.move(400,440)

        self.setWindowTitle('making ppt Automation program - Omega Church')
        self.setWindowIcon(QIcon('omega_logo.png'))
        self.resize(900, 490) #사이즈 조절
        self.center() #센터 맞춘다.

        cb_value = str(cb.currentText())
        chapter_value = str(chapter.text())
        start_value = str(start.text())
        end_value = str(end.text())
        title_value = str(title.text())

        btn.clicked.connect(lambda: self.make(str(title.text()),str(cb.currentText()),str(chapter.text()),str(start.text()),str(end.text()),))

        self.show() #창을 띄운다.

    def onActivated(self, text):
        self.lbl.setText(text)
        self.lbl.adjustSize()

    def center(self): #창을 가운데 띄우기위한 함수
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())

    def make(self,input_title, input_chapter, input_number, input_start, input_end):
        mytitle = input_title
        Chapter = input_chapter
        number = int(input_number)
        start = int(input_start)
        end = int(input_end)
        '''
        장 마다 바꿔주어야 할 값은 위의 두 값밖에 없다. 대신 값들은 정확해야한다.
        Chapter = 장 이름
        howMany = 장 수
        '''
        print("Title : " + Chapter)
        print("<<< Start >>>")
        print()

        txtName = Chapter + str(object=number) + "장"
        pptPath = "D:/minwoo/my_study/ppt_study/test.pptx"

        txtPath = txtName+'.txt'
        txtPath = os.path.abspath(txtPath)

        print(txtPath)

        name = str(copy.deepcopy(txtPath).rsplit('\\', 1)[1].split('.')[0])

        print(name)
        words = []

        try:
            targetFile = open(txtPath, 'rt', encoding='UTF-8')
            lines = targetFile.readlines()

            for line in lines:
                words.append(line.strip())  # word 배열에 한줄씩 저장
            targetFile.close()
        except IOError as err:
            print("Server File Error: " + str(err))
            # 여기까지 왔으면 Words에 말씀 저장완료


        try: #디렉터리 생성
            if not(os.path.isdir(Chapter)):
                os.makedirs(os.path.join(Chapter))
        except OSError as e:
            if e.errno != errno.EEXIST:
                print("Failed to create directory!!!!!")
                raise


        # 인자로 불러올 파일 경로를 넣어줄 수 있다. 인자 없으면 현재 경로에 새롭게 생성
        prs = Presentation(pptPath)
        #prs = Presentation(pptPath)
        title_slide_layout = prs.slide_layouts[0]  # 제목 슬라이드 레이아웃 지정 Layout 0번이다.
        count=1
        for contents in words:
            (verse, word) = contents.split('.', 1)
            if count >=start and count <=end:
                # 그 레이아웃으로 슬라이드 하나 추가하고 그걸 slide변수라고 지정
                slide = prs.slides.add_slide(title_slide_layout)
                title = slide.shapes.title  # 제목 상자 지정
                subtitle = slide.placeholders[1]  # 부제목 상자 지정
                title.text = str(mytitle)  # 제목 텍스트 적는다.
                subtitle.text = str(verse) + "\n" + str(word.strip())  # 부제목 텍스트 적는다.
                count += 1
            else:
                count += 1

        prs.save(Chapter + './' + str(mytitle) + '.pptx')

if __name__ == '__main__':

    app = QApplication(sys.argv) #어플리케이션 객체 생성
    ex = MyApp()
    sys.exit(app.exec_())
