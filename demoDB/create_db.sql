DROP ROLE IF EXISTS demo;
create user demo with password 'demo';
drop database if exists amazingdemo;
create database amazingdemo with owner demo;