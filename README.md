# githubpage2html-web

Web interface for pet project [githubpage2html](https://github.com/carouselapps/githubpage2html).

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein run

To deploy to Heroku:

* `lein uberjar` to compile it
* Test it with `foreman start`
* `git push heroku master`

## Live site

https://githubpage2html.herokuapp.com/

## License

Copyright © 2015 Carousel Apps
