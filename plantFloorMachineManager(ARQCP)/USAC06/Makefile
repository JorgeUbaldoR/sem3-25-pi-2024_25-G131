main: files/main.o files/usac06.o | files
	gcc files/main.o files/usac06.o -z noexecstack -o files/main

files/main.o: src/main.c | files
	gcc -g -Wall -Wextra -fanalyzer -c src/main.c -o files/main.o

files/usac06.o: src/usac06.s | files
	gcc -g -Wall -Wextra -fanalyzer -c src/usac06.s -o files/usac06.o

files/test_usac06.o: tests/test_usac06.c | files
	gcc -g -Wall -Wextra -fanalyzer -c tests/test_usac06.c -o files/test_usac06.o

files/unity.o: ../unity_folder/unity.c | files
	gcc -g -Wall -Wextra -fanalyzer -c ../unity_folder/unity.c -o files/unity.o

tests: files/test_usac06.o files/unity.o files/usac06.o
	gcc files/test_usac06.o files/usac06.o files/unity.o -o files/test_executable

files:
	mkdir -p files

run: main
	files/./main

clean:
	rm -f files/*.o files/main files/test_executable

unity_tests: tests
	files/./test_executable
