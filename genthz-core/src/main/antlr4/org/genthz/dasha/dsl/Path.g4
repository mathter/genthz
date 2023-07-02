/*
 * Generated - testing becomes easier
 *
 * Copyright (C) 2020 mathter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
grammar Path;

path
    :   root | (root? pathElement (PATH_SEPARATOR pathElement)*)
    ;

root
    :   PATH_SEPARATOR
    ;

pathElement
    :   (staticElement indexedElement* | matchedElement indexedElement* | skipSequnce | constructor)
    ;

constructor
    :   'c{' COUNT '}'
    ;

staticElement
    :   IDENTIFIER
    ;

matchedElement
    :   MATCHED_SYMBOLS
    ;

indexedElement
    :   '[' COUNT ']'
    ;

index
    :   SKIP_NAME_COUNT
    ;

skipSequnce
    :   skipElement (PATH_SEPARATOR skipElement)*
    ;

skipElement
    :   SKIP_NAME COUNT?
    ;

SKIP_NAME
    :   '..'
    ;

COUNT
    :   DIGIT+
    ;

MATCHED_SYMBOLS
    :   JavaLetter* (ANY | ANY JavaLetterOrDigit+)+
    ;

IDENTIFIER
    : JavaLetter+ JavaLetterOrDigit*
    ;

DIGIT
    :   [0-9]
    ;

PATH_SEPARATOR
    :   '/'
    ;

ANY
    :   '*'
    ;

fragment
JavaLetter
	:	[a-zA-Z$_] // these are the "java letters" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierStart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

fragment
JavaLetterOrDigit
	:	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierPart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;