#include <QtWidgets>
#include "widget.h"
#include <QWindow>
#include <cstring>
static void handleVisibleChanged(){
    if (!QGuiApplication::inputMethod()->isVisible())
        return;
    for(QWindow * w: QGuiApplication::allWindows()){
        if(std::strcmp(w->metaObject()->className(), "QtVirtualKeyboard::InputView") == 0){
            if(QObject *keyboard = w->findChild<QObject *>("keyboard")){
                QRect r = w->geometry();
                r.moveTop(keyboard->property("y").toDouble());
                w->setMask(r);
                return;
            }
        }
    }
}
// https://github.com/rutura/Qt-6-C-GUI-Development-for-Beginners
int main(int argc, char *argv[])
{
    qputenv("QT_IM_MODULE", QByteArray("qtvirtualkeyboard"));
    // Creates an instance of QApplication
    QApplication a(argc, argv);
    QObject::connect(QGuiApplication::inputMethod(), &QInputMethod::visibleChanged, &handleVisibleChanged);


    // This is our MainWidget class containing our GUI and functionality
    widget w;
    w.show(); // Show main window

    // run the application and return execs() return value/code
    return a.exec();
}
