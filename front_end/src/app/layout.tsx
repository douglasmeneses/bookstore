import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "Book Store",
  description: "Livraria On-line",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className="min-h-full flex flex-col">{children}</body>
    </html>
  );
}
