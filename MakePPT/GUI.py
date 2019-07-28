import sys
from PyQt5.QtWidgets import (QApplication, QComboBox, QVBoxLayout,QWidget, QPushButton, QDesktopWidget,QGridLayout, QLabel, QLineEdit, QTextEdit)
from PyQt5.QtGui import QIcon, QFont
from PyQt5.QtCore import QCoreApplication
import sys
import io
#import makePPT

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
        btn.clicked.connect(lambda: self.testFunction(str(cb.currentText()),str(chapter.text()),str(start.text()),str(end.text()),str(title.text())))
        self.show() #창을 띄운다.

    def onActivated(self, text):
        self.lbl.setText(text)
        self.lbl.adjustSize()

    def center(self): #창을 가운데 띄우기위한 함수
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())


    def testFunction(self,words,chapter,start,end,title):

        print(str(words))
        print(str(chapter))
        print(str(start))
        print(str(end))
        print(str(title))

        try:  # Server 파일목록 텍스트파일 읽어와서 파일명 파싱해서 저장
            targetFile = open("testingfile.txt", 'w', encoding='UTF-8')
            targetFile.write(words)
            targetFile.write(chapter)
            targetFile.close()
        except IOError as err:
            print("Server File Error: " + str(err))



if __name__ == '__main__':

    app = QApplication(sys.argv) #어플리케이션 객체 생성
    ex = MyApp()
    sys.exit(app.exec_())
