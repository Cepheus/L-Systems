#!/bin/sh

echo "<!DOCTYPE html>
<html>
<head>
	<title>Readme L-System</title>
	<meta charset="UTF-8" />
</head>
<body>
" > README.html

markdown README >> README.html

echo "
</body>
</html>
" >> README.html


