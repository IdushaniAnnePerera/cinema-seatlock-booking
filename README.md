# Cinema SeatLock Booking (Industry-grade starter)

Monorepo with Spring Boot 3 + React/Vite implementing movie discovery, real-time seat locking, booking/payment, QR ticketing/check-in, and admin analytics.

## Tech Stack
- **Backend**: Spring Boot 3, Spring Security, JWT access/refresh, MongoDB, Redis, STOMP WebSocket, Swagger/OpenAPI, Stripe SDK
- **Frontend**: React + Vite + TypeScript, TailwindCSS (dark default), TanStack Query, React Router, Recharts

## Features
- Customer: browse movies/showtimes, select seats, hold (5 min), confirm booking, ticket token
- Admin: manage movies/cinemas/screens/layouts/showtimes/promos + analytics endpoint
- Staff: validate/check-in ticket token (prevents reuse)
- Real-time seat state topics: `/topic/showtimes/{showtimeId}`
- Seeded starter data and role users

## Local Run
Run backend and frontend separately in your local environment:

- Backend API: http://localhost:8080
- Frontend: http://localhost:5173
- Swagger: http://localhost:8080/swagger-ui

### Seeded Credentials
- admin@cinema.io / Admin@123
- staff@cinema.io / Staff@123
- customer@cinema.io / Customer@123

## Architecture (ASCII)
```text
[React SPA] --HTTP/JWT--> [Spring Boot API] --Mongo--> [Collections]
      |                       |                 users, movies, showtimes,
      +----STOMP WS---------->+                 seat_holds, bookings, ...
                              |
                              +--Redis (seat hold TTL + lock keys)
                              +--Stripe Checkout/Webhook
                              +--Email provider abstraction (extensible)
```

## Booking Flow
1. Customer selects seats on showtime screen
2. `POST /api/bookings/hold`: checks conflicts, creates Mongo hold + Redis TTL (5m), broadcasts WS update
3. `POST /api/bookings/confirm`: validates ownership/not expired, creates booking/payment/ticket, marks hold converted
4. Staff/Admin validate token via `POST /api/tickets/validate` and ticket gets `checkedInAt`
5. Scheduler releases expired holds every minute

## Stripe dev notes
- Endpoint: `POST /api/payments/stripe/session`
- Webhook endpoint: `POST /api/payments/webhook/stripe`
- Use Stripe CLI:
```bash
stripe listen --forward-to localhost:8080/api/payments/webhook/stripe
```

## Free-tier-first deployment plan
- **MongoDB**: Atlas M0
- **Redis**: Upstash Redis free tier
- **Backend**: Render / Railway / Fly.io free-tier candidate
- **Frontend**: Vercel or Netlify
- **Payments**: Stripe test mode
- **Email**: Mailtrap (dev), Resend/SendGrid free tier in prod
- **SMS optional**: Twilio trial using interface/stub pattern

## Important Env
- Backend: `MONGODB_URI`, `REDIS_HOST`, `JWT_SECRET`, `STRIPE_SECRET`, `STRIPE_WEBHOOK_SECRET`
- Frontend: `VITE_API_URL`

## API Quick Index
- Auth: `/api/auth/register|login|refresh`
- Public: `/api/public/movies`, `/api/public/movies/{id}/showtimes`
- Booking: `/api/bookings/hold`, `/api/bookings/confirm`
- Ticket check-in: `/api/tickets/validate`
- Admin: `/api/admin/*`, `/api/admin/analytics`
