# SearchStringInFiles
###hh-school homework 2 

Для решения данной задачи используется самый простой вариант: последовательное чтения файлов с выводом номеров строк, содержащих нужную подстроку.

Перед тем, как выбрать данную реализацию окончательной, я протестировал два варианта:

1) Многопоточное выполнение поиска, где поиск по отдельным файлам выполнялся в разных потоках.
Данный подход себя не оправдал, так как программа упиралась в скорость ввода/вывода, т.е. работу диска. 
Более того, при наличии очень больших файлов сразу была заметна просадка по производительности.

2) Последовательное чтение файлов с многопоточной обработкой поиска по подстрокам.
Данный подход теоретически может дать прибавку в скорости. Но реализация предполагает сложный механизм синхронизаци,
добавление дополнительных структур данных и так далее, что в конечном итоге сильно сказывается на производительности.

