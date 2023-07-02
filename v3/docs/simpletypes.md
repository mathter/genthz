---
title: Simple types
description: Simple types...
layout: libdoc/page

category: Documentation V3
order: 9001
---
### Simple types
If constructor calling is enough to instance creating and filling fields of the class than this one is simple type.
All primitives are simple types also.

There is default realization for generation of simple types (see [DashaDsl.def()](../apidocs/org/genthz/dasha/dsl/DashaDsl.html#def()){:target="_blank"}.
This method [DashaDsl.def()](../apidocs/org/genthz/dasha/dsl/DashaDsl.html#def()){:target="_blank"} uses
[DashaDefaults](../apidocs/org/genthz/dasha/DashaDefaults.html){:target="_blank"} to create default instance builders for filler.
Thus you can override any of methods [DashaDefaults](../apidocs/org/genthz/dasha/DashaDefaults.html){:target="_blank"} or implements
[Defaults](../apidocs/org/genthz/Defaults.html){:target="_blank"} directly.