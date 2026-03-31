# Проект для тестирования бесплатного способа использования ИИ агенотв для написания кода.

Содержание проекта
- test_task - тестовое задание которое будет даваться ии агенту
- qwen_remote-qwen_code - результат для конфига облачная модель qwen и qwen_code
- qwen_remote-kilo_code - результат для конфига облачная модель qwen  и kilo code плагин для VS Code
- qwen_remote-claude-code -  результат для  конфига облачная модель qwen и  claude-code CLI
- qwen_local-claude-code - локальная модель qwen3-coder-30b-a3b-instruct-mlx и claude-code CLI
- qwen_local-Kilo_code - локальная модель qwen3-coder-30b-a3b-instruct-mlx и kilo code плагин для VS Code 

Доп материалы
- config.json - файл для конфигурации claude-code-router и qwen
- update_qwen_token.sh - баш скрипт который обнавляет токен qwen в конфигурации claude-code-router

Результаты работы по времени:
- qwen_remote-qwen_code - 1-2 минут
- qwen_remote-kilo_code - 1-2 минут
- qwen_remote-claude_code - 1-2 минут
- qwen_local-claude-code - 10-15 минут -  данное решение не пригодно для использования из-за своей скорости
- qwen_local-Kilo_code - 1-2 минут