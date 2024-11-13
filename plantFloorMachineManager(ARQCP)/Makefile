UI.o: UI.c
	gcc -g -Wall -Wextra -c UI.c -o UI.o

USAC01/files/usac01.o: USAC01/src/usac01.s
	gcc -g -Wall -Wextra -c USAC01/src/usac03.s -o USAC01/files/usac01.o

USAC02/files/usac02.o: USAC02/src/usac02.s
	gcc -g -Wall -Wextra -c USAC02/src/usac02.s -o USAC02/files/usac02.o

USAC03/files/usac03.o: USAC03/src/usac03.s
	gcc -g -Wall -Wextra -c USAC03/src/usac03.s -o USAC03/files/usac03.o

USAC06/files/usac06.o: USAC06/src/usac06.s
	gcc -g -Wall -Wextra -c USAC06/src/usac06.s -o USAC06/files/usac06.o

USAC07/files/usac07.o: USAC07/src/usac07.s
	gcc -g -Wall -Wextra -c USAC07/src/usac07.s -o USAC07/files/usac07.o

USAC09/files/usac09.o: USAC09/src/usac09.s
	gcc -g -Wall -Wextra -c USAC09/src/usac09.s -o USAC09/files/usac09.o

USAC10/files/usac10.o: USAC09/src/usac09.s
	gcc -g -Wall -Wextra -c USAC10/src/usac10.s -o USAC10/files/usac10.o

UI: UI.o USAC09/files/usac09.o USAC03/files/usac03.o USAC06/files/usac06.o USAC07/files/usac07.o USAC01/files/usac01.o USAC02/files/usac02.o USAC10/files/usac10.o
	gcc UI.o USAC09/files/usac09.o USAC03/files/usac03.o USAC06/files/usac06.o USAC07/files/usac07.o USAC01/files/usac01.o USAC02/files/usac02.o USAC10/files/usac10.o -o UI

run: UI
	./UI

clean:
	rm -f *.o UI
