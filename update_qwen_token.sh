#!/bin/bash

# ============================================
# Скрипт для обновления api_key в config.json
# из oauth_creds.json от Qwen CLI
# ============================================

set -e  # прерывать при ошибках

OAUTH_FILE="~/.qwen/oauth_creds.json"
CONFIG_FILE="~/.claude-code-router/config.json"
BACKUP_FILE="${CONFIG_FILE}.bak"

# Проверка наличия файлов
for file in "$OAUTH_FILE" "$CONFIG_FILE"; do
    if [ ! -f "$file" ]; then
        echo "❌ Ошибка: Файл $file не найден."
        exit 1
    fi
done

# Извлечение access_token
if command -v jq &> /dev/null; then
    # Используем jq (надёжно)
    ACCESS_TOKEN=$(jq -r '.access_token' "$OAUTH_FILE")
    if [ -z "$ACCESS_TOKEN" ] || [ "$ACCESS_TOKEN" = "null" ]; then
        echo "❌ Не удалось извлечь access_token через jq. Проверьте структуру JSON."
        exit 1
    fi
else
    # Альтернатива без jq (менее надёжно)
    echo "⚠️ jq не установлен. Пытаемся извлечь токен через grep/sed..."
    ACCESS_TOKEN=$(grep -o '"access_token"[[:space:]]*:[[:space:]]*"[^"]*"' "$OAUTH_FILE" | sed -E 's/.*"access_token"[[:space:]]*:[[:space:]]*"([^"]*)".*/\1/')
    if [ -z "$ACCESS_TOKEN" ]; then
        echo "❌ Не удалось извлечь access_token. Установите jq или проверьте файл вручную."
        exit 1
    fi
fi

echo "🔑 Извлечён access_token: ${ACCESS_TOKEN:0:15}... (первые 15 символов)"

# Создание резервной копии
cp "$CONFIG_FILE" "$BACKUP_FILE"
echo "💾 Создана резервная копия: $BACKUP_FILE"

# Обновление api_key в config.json
if command -v jq &> /dev/null; then
    # jq: обновляем поле api_key у первого провайдера
    jq --arg token "$ACCESS_TOKEN" '.Providers[0].api_key = $token' "$CONFIG_FILE" > "${CONFIG_FILE}.tmp" && mv "${CONFIG_FILE}.tmp" "$CONFIG_FILE"
    echo "✅ Файл обновлён с помощью jq."
else
    # sed: заменяем строку "api_key": "старый_токен"
    echo "⚠️ Обновляем через sed (будьте внимательны, формат должен быть точно как в примере)."
    # Экранируем спецсимволы в токене для безопасной подстановки в sed
    ESCAPED_TOKEN=$(printf '%s\n' "$ACCESS_TOKEN" | sed 's/[\/&]/\\&/g')
    sed -i '' -E "s/(\"api_key\"[[:space:]]*:[[:space:]]*\")[^\"]*(\")/\1$ESCAPED_TOKEN\2/" "$CONFIG_FILE"
    echo "✅ Файл обновлён через sed (рекомендуется проверить результат вручную)."
fi

# Проверка, что токен действительно заменился (опционально)
if grep -q "$ACCESS_TOKEN" "$CONFIG_FILE"; then
    echo "🎉 Токен успешно записан в $CONFIG_FILE"
else
    echo "⚠️ Внимание: не удалось подтвердить наличие нового токена в файле. Проверьте вручную."
fi

echo "Готово. Можете перезапустить Claude Code Router, если он запущен."