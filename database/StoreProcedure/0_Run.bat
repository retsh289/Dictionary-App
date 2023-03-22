@echo off
@echo Executing Files
@echo off
@echo Excuting
for %%G in (*.sql) do sqlcmd -S LAPTOP-P4M4QJ81\NGUYENPHU -d Dictionary -E -i "%%G"
Pause