FROM ubuntu

RUN apt update
RUN apt -y upgrade
RUN apt -y install ruby-full build-essential zlib1g-dev
RUN gem install jekyll bundler nokogiri jekyll-toc jekyll-remote-theme

EXPOSE 4000

ENTRYPOINT jekyll serve --host 0.0.0.0 -s /mnt