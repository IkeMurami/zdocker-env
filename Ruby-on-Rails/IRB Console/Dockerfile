FROM ruby:2.7.1-alpine

RUN apk add --update --no-cache \
    binutils-gold \
    build-base \
    postgresql-dev

WORKDIR /app

COPY Gemfile Gemfile
COPY Gemfile.lock Gemfile.lock

RUN gem install bundler
# Install All Dependency
RUN bundle install